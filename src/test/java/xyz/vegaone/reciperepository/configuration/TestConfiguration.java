package xyz.vegaone.reciperepository.configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.vegaone.reciperepository.repo.IngredientRepository;

@Configuration
public class TestConfiguration {
    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

    @Bean
    public IngredientRepository ingredientRepository() {
        return Mockito.mock(IngredientRepository.class);
    }
}
