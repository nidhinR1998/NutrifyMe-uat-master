package com.application.dietfood.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.application.dietfood.Dto.AltMeasureDTO;
import com.application.dietfood.Dto.FoodDTO;
import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.FoodResponseDTO;
import com.application.dietfood.Dto.FullNutrientDTO;
import com.application.dietfood.Dto.MetadataDTO;
import com.application.dietfood.Dto.PhotoDTO;
import com.application.dietfood.Dto.ResponseDietDetails;
import com.application.dietfood.Dto.TagsDTO;
import com.application.dietfood.service.NutritionService;

@Service
public class NutritionServiceImpl implements NutritionService {

    private static final Logger logger = LoggerFactory.getLogger(NutritionServiceImpl.class);

    @Value("${nutritionix.api.app_id}")
    private String appId;

    @Value("${nutritionix.api.key}")
    private String apiKey;

    @Value("${nutritionix.api.url}")
    private String apiUrl;

    @Autowired
    private final RestTemplate restTemplate;

    public NutritionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseDietDetails getNutritionalInfo(FoodItemDto foodItemDto) {
        logger.info("Fetching nutritional information for food item: {} of type: {}", foodItemDto.getFoodname(), foodItemDto.getType());

        // Construct the request URL
        String url = apiUrl;

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-id", appId); // Set App ID
        headers.set("x-app-key", apiKey); // Set API Key
        headers.set("Content-Type", "application/json");

        // Create request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", foodItemDto.getFoodname());

        // Create HttpEntity with headers and body
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Send POST request
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
            Map<String, Object> response = responseEntity.getBody();
            logger.info("Response from API: {}", response);

            // Convert the response to FoodResponseDTO
            FoodResponseDTO foodResponseDTO = mapResponseToFoodResponseDTO(response);

            // Extract and process nutrients
            ResponseDietDetails dto = extractNutrients(foodResponseDTO);
            
            StringBuilder recommendations = new StringBuilder();

            // Recommendations based on food type
            if ("non-veg".equalsIgnoreCase(foodItemDto.getType())) {
                logger.info("Processing non-veg item: {}", foodItemDto.getFoodname());

                // High-protein recommendation
                if (dto.getProtein() != null && dto.getProtein() > 30) {
                    recommendations.append("This non-veg item is high in protein. Good choice for muscle building. ");
                } else {
                    recommendations.append("Consider adding more protein-rich foods to your diet such as chicken breast, lean beef, or salmon. ");
                }

                // Low-fat recommendation
                if (dto.getFat() != null && dto.getFat() < 10) {
                    recommendations.append("This item is low in fat, making it a healthier choice. ");
                } else {
                    recommendations.append("Be mindful of the fat content. Opt for lean meats like turkey, cod, or skinless chicken. ");
                }

                // Cholesterol recommendation
                if (dto.getCholesterol() != null && dto.getCholesterol() > 100) {
                    recommendations.append("This item is high in cholesterol. Limit your intake to maintain heart health. Consider alternatives like grilled chicken or turkey. ");
                }
            } else if ("veg".equalsIgnoreCase(foodItemDto.getType())) {
                logger.info("Processing veg item: {}", foodItemDto.getFoodname());

                // High-fiber recommendation
                if (dto.getFiber() != null && dto.getFiber() > 5) {
                    recommendations.append("This veg item is high in fiber, which is great for digestion. ");
                } else {
                    recommendations.append("Include more fiber-rich vegetables like broccoli, Brussels sprouts, or beans in your diet. ");
                }

                // Vitamin C recommendation
                if (dto.getVitaminC() != null && dto.getVitaminC() > 50) {
                    recommendations.append("This item is rich in Vitamin C, which is essential for immune health. ");
                } else {
                    recommendations.append("Consider consuming more fruits and vegetables rich in Vitamin C like oranges, bell peppers, or strawberries. ");
                }

                // Vitamin E recommendation
                if (dto.getVitaminE() != null && dto.getVitaminE() > 5) {
                    recommendations.append("This item is a good source of Vitamin E, which acts as an antioxidant. ");
                } else {
                    recommendations.append("Including more antioxidant-rich foods like almonds, spinach, or avocados could benefit your overall health. ");
                }
            } else {
                recommendations.append("Nutritional information available. Make sure to balance your diet with a variety of foods. ");
            }

            // Additional logic for generic recommendations
            if (dto.getCalories() != null && dto.getCalories() > 500) {
                recommendations.append("This item is high in calories. Consider portion control. ");
            }

            if (dto.getSugar() != null && dto.getSugar() > 20) {
                recommendations.append("This item has high sugar content. Monitor your sugar intake. ");
            }

            // Calcium recommendation
            if (dto.getCalcium() != null && dto.getCalcium() < 200) {
                recommendations.append("This item is low in calcium. Consider including more calcium-rich foods like leafy greens, fortified plant milks, or yogurt. ");
            }

            // Potassium recommendation
            if (dto.getPotassium() != null && dto.getPotassium() < 300) {
                recommendations.append("This item has low potassium. Ensure you get enough potassium from other foods like bananas, sweet potatoes, or avocados. ");
            }

            // Balanced diet recommendation
            if (dto.getCalories() != null && dto.getProtein() != null && dto.getFat() != null) {
                double calorieRatio = (dto.getProtein() * 4 + dto.getFat() * 9) / dto.getCalories();
                if (calorieRatio > 0.5) {
                    recommendations.append("This item has a high protein-to-calorie ratio, which may be beneficial for muscle maintenance. ");
                }
            }

            dto.setRecommendations(recommendations.toString());
            return dto;

        } catch (HttpClientErrorException e) {
            logger.error("API request failed with status: {}", e.getStatusCode());
            logger.error("Response body: {}", e.getResponseBodyAsString());
            throw e; // Or handle the error as appropriate
        }
    }

    private FoodResponseDTO mapResponseToFoodResponseDTO(Map<String, Object> response) {
        FoodResponseDTO foodResponseDTO = new FoodResponseDTO();
        List<FoodDTO> foodDTOList = new ArrayList<>();
        
        if (response != null && response.containsKey("foods")) {
            List<Map<String, Object>> foodsList = (List<Map<String, Object>>) response.get("foods");
            
            for (Map<String, Object> foodMap : foodsList) {
                FoodDTO foodDTO = new FoodDTO();
                
                // Map fields with null checks
                foodDTO.setFood_name((String) foodMap.get("food_name"));
                foodDTO.setBrand_name((String) foodMap.get("brand_name"));
                foodDTO.setServing_qty(getIntValue(foodMap.get("serving_qty")));
                foodDTO.setServing_unit((String) foodMap.get("serving_unit"));
                foodDTO.setServing_weight_grams(getDoubleValue(foodMap.get("serving_weight_grams")));
                foodDTO.setNf_calories(getDoubleValue(foodMap.get("nf_calories")));
                foodDTO.setNf_total_fat(getDoubleValue(foodMap.get("nf_total_fat")));
                foodDTO.setNf_saturated_fat(getDoubleValue(foodMap.get("nf_saturated_fat")));
                foodDTO.setNf_cholesterol(getDoubleValue(foodMap.get("nf_cholesterol")));
                foodDTO.setNf_sodium(getDoubleValue(foodMap.get("nf_sodium")));
                foodDTO.setNf_total_carbohydrate(getDoubleValue(foodMap.get("nf_total_carbohydrate")));
                foodDTO.setNf_dietary_fiber(getDoubleValue(foodMap.get("nf_dietary_fiber")));
                foodDTO.setNf_sugars(getDoubleValue(foodMap.get("nf_sugars")));
                foodDTO.setNf_protein(getDoubleValue(foodMap.get("nf_protein")));
                foodDTO.setNf_potassium(getDoubleValue(foodMap.get("nf_potassium")));
                foodDTO.setNf_p(getDoubleValue(foodMap.get("nf_p")));

                // Map full_nutrients
                List<Map<String, Object>> fullNutrientsList = (List<Map<String, Object>>) foodMap.get("full_nutrients");
                List<FullNutrientDTO> fullNutrientsDTOList = new ArrayList<>();
                if (fullNutrientsList != null) {
                    for (Map<String, Object> nutrientMap : fullNutrientsList) {
                        FullNutrientDTO fullNutrientDTO = new FullNutrientDTO();
                        fullNutrientDTO.setAttr_id(getIntValue(nutrientMap.get("attr_id")));
                        fullNutrientDTO.setValue(getDoubleValue(nutrientMap.get("value")));
                        fullNutrientsDTOList.add(fullNutrientDTO);
                    }
                }
                foodDTO.setFull_nutrients(fullNutrientsDTOList);

                // Map other fields
                foodDTO.setNix_brand_name((String) foodMap.get("nix_brand_name"));
                foodDTO.setNix_brand_id((String) foodMap.get("nix_brand_id"));
                foodDTO.setNix_item_name((String) foodMap.get("nix_item_name"));
                foodDTO.setNix_item_id((String) foodMap.get("nix_item_id"));
                foodDTO.setUpc((String) foodMap.get("upc"));
                foodDTO.setConsumed_at((String) foodMap.get("consumed_at"));

                // Map metadata
                Map<String, Object> metadataMap = (Map<String, Object>) foodMap.get("metadata");
                if (metadataMap != null) {
                    MetadataDTO metadataDTO = new MetadataDTO();
                    metadataDTO.setIs_raw_food((Boolean) metadataMap.get("is_raw_food"));
                    foodDTO.setMetadata(metadataDTO);
                }

                // Map tags
                Map<String, Object> tagsMap = (Map<String, Object>) foodMap.get("tags");
                if (tagsMap != null) {
                    TagsDTO tagsDTO = new TagsDTO();
                    tagsDTO.setItem((String) tagsMap.get("item"));
                    tagsDTO.setMeasure((String) tagsMap.get("measure"));
                    tagsDTO.setQuantity((String) tagsMap.get("quantity"));
                 // Convert String to Double
                    //tagsDTO.setQuantity(getDoubleValue(tagsMap.get("quantity")));
                   // Convert Double to String
                   // tagsDTO.setQuantity(String.valueOf(getDoubleValue(tagsMap.get("quantity")))); 
                    tagsDTO.setFood_group(getIntValue(tagsMap.get("food_group")));
                    tagsDTO.setTag_id(getIntValue(tagsMap.get("tag_id")));
                    foodDTO.setTags(tagsDTO);
                }

                // Map alt_measures
                List<Map<String, Object>> altMeasuresList = (List<Map<String, Object>>) foodMap.get("alt_measures");
                List<AltMeasureDTO> altMeasuresDTOList = new ArrayList<>();
                if (altMeasuresList != null) {
                    for (Map<String, Object> altMeasureMap : altMeasuresList) {
                        AltMeasureDTO altMeasureDTO = new AltMeasureDTO();
                        altMeasureDTO.setServing_weight(getDoubleValue(altMeasureMap.get("serving_weight")));
                        altMeasureDTO.setMeasure((String) altMeasureMap.get("measure"));
                        altMeasureDTO.setSeq(getIntValue(altMeasureMap.get("seq")));
                        altMeasureDTO.setQty(getDoubleValue(altMeasureMap.get("qty")));
                        altMeasuresDTOList.add(altMeasureDTO);
                    }
                }
                foodDTO.setAlt_measures(altMeasuresDTOList);

                // Add foodDTO to list
                foodDTOList.add(foodDTO);
            }
        }
        foodResponseDTO.setFoods(foodDTOList);
        return foodResponseDTO;
    }

    private Integer getIntValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return null;
    }

    private Double getDoubleValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return null;
    }


    private ResponseDietDetails extractNutrients(FoodResponseDTO response) {
        ResponseDietDetails dto = new ResponseDietDetails();

        if (response != null && response.getFoods() != null && !response.getFoods().isEmpty()) {
            FoodDTO food = response.getFoods().get(0);
            List<FullNutrientDTO> foodNutrients = food.getFull_nutrients();

            // Handle serving size
            dto.setServingSize(food.getServing_qty());

            // Handle serving unit
            dto.setServingUnit(food.getServing_unit());

            // Handle servings per container
            dto.setServingsPerContainer(food.getServing_qty()); // Assuming servings per container is the same as serving_qty
            
            processNutrients(foodNutrients, dto);

            // Handle nutrient values
            if (foodNutrients != null) {
                for (FullNutrientDTO nutrient : foodNutrients) {
                    String nutrientName = getNutrientName(nutrient.getAttr_id());
                    Double nutrientValue = nutrient.getValue();

                    if (nutrientValue != null) {
                        switch (nutrientName.toLowerCase()) {
                            case "calories":
                                dto.setCalories(nutrientValue);
                                break;
                            case "protein":
                                dto.setProtein(nutrientValue);
                                break;
                            case "carbohydrates":
                                dto.setCarbohydrates(nutrientValue);
                                break;
                            case "fat":
                                dto.setFat(nutrientValue);
                                break;
                            case "fiber":
                                dto.setFiber(nutrientValue);
                                break;
                            case "sugar":
                                dto.setSugar(nutrientValue);
                                break;
                            case "salt":
                                dto.setSalt(nutrientValue);
                                break;
                            case "cholesterol":
                                dto.setCholesterol(nutrientValue);
                                break;
                            case "calcium":
                                dto.setCalcium(nutrientValue);
                                break;
                            case "iron":
                                dto.setIron(nutrientValue);
                                break;
                            case "potassium":
                                dto.setPotassium(nutrientValue);
                                break;
                            case "vitamin a":
                                dto.setVitaminA(nutrientValue);
                                break;
                            case "vitamin c":
                                dto.setVitaminC(nutrientValue);
                                break;
                            case "vitamin d":
                                dto.setVitaminD(nutrientValue);
                                break;
                            case "vitamin e":
                                dto.setVitaminE(nutrientValue);
                                break;
                            case "vitamin k":
                                dto.setVitaminK(nutrientValue);
                                break;
                            default:
                                logger.warn("Unknown nutrient: {}", nutrientName);
                                break;
                        }
                    }
                }
            }
        }

        return dto;
    }

    private void processNutrients(List<FullNutrientDTO> nutrients, ResponseDietDetails dto) {
        if (nutrients != null) {
            for (FullNutrientDTO nutrient : nutrients) {
                String nutrientName = getNutrientName(nutrient.getAttr_id());
                Double nutrientValue = nutrient.getValue();

                if (nutrientValue != null) {
                    switch (nutrientName.toLowerCase()) {
                        case "calories":
                            dto.setCalories(nutrientValue);
                            break;
                        case "protein":
                            dto.setProtein(nutrientValue);
                            break;
                        case "carbohydrates":
                            dto.setCarbohydrates(nutrientValue);
                            break;
                        case "fat":
                            dto.setFat(nutrientValue);
                            break;
                        case "fiber":
                            dto.setFiber(nutrientValue);
                            break;
                        case "sugar":
                            dto.setSugar(nutrientValue);
                            break;
                        case "salt":
                            dto.setSalt(nutrientValue);
                            break;
                        case "cholesterol":
                            dto.setCholesterol(nutrientValue);
                            break;
                        case "calcium":
                            dto.setCalcium(nutrientValue);
                            break;
                        case "iron":
                            dto.setIron(nutrientValue);
                            break;
                        case "potassium":
                            dto.setPotassium(nutrientValue);
                            break;
                        case "vitamin a":
                            dto.setVitaminA(nutrientValue);
                            break;
                        case "vitamin c":
                            dto.setVitaminC(nutrientValue);
                            break;
                        case "vitamin d":
                            dto.setVitaminD(nutrientValue);
                            break;
                        case "vitamin e":
                            dto.setVitaminE(nutrientValue);
                            break;
                        case "vitamin k":
                            dto.setVitaminK(nutrientValue);
                            break;
                        default:
                            logger.warn("Nutrient with ID {} is unknown or not mapped. Value: {}", nutrient.getAttr_id(), nutrientValue);
                            break;
                    }
                }
            }
        }
    }


    private String getNutrientName(int attr_id) {
        // Existing mappings
        switch (attr_id) {
            case 203: return "protein";
            case 204: return "fat";
            case 205: return "carbohydrates";
            case 208: return "calories";
            case 291: return "fiber";
            case 269: return "sugar";
            case 307: return "salt";
            case 601: return "cholesterol";
            case 301: return "calcium";
            case 303: return "iron";
            case 306: return "potassium";
            case 319: return "vitamin a";
            case 320: return "vitamin c";
            case 321: return "vitamin d";
            case 322: return "vitamin e";
            case 323: return "vitamin k";
            // Add new mappings based on recent API documentation
            case 207: return "nutrient_x"; // Update with actual name
            case 209: return "nutrient_y"; // Update with actual name
            case 210: return "nutrient_z"; // Update with actual name
            case 211: return "nutrient_w"; // Update with actual name
            default:
                logger.warn("Unknown nutrient ID: {}. Please check the API documentation for this ID.", attr_id);
                return "unknown";
        }
    }
}

