package kometa.statistic.workstationstatisticserver.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Учет посещаемости и успеваемости студентов")
                    .version("1.0")
                    .description("Документация API системы учета посещаемости и успеваемости студентов")
            )
    }
}