package pl.edu.agh.rea.crawler.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirestoreConfiguration {

    @Bean
    fun firestoreDb(@Value("\${FIRESTORE_CREDENTIALS_PATH}") credentialsFilePath: String,
                    @Value("\${FIRESTORE_PROJECT_ID}") projectId: String): Firestore {
        return FirestoreOptions
                .getDefaultInstance()
                .toBuilder()
                .setCredentials(GoogleCredentials.fromStream(FileInputStream(credentialsFilePath)))
                .setProjectId(projectId)
                .build()
                .service
    }

}