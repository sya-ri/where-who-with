import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

/**
 * where-who-with のビルドスクリプトを拡張するプラグイン
 */
open class W3Plugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            val generatedResourcesDirectory = layout.buildDirectory.dir("generated/w3")
            val generateVersionFileTask = tasks.register<GenerateVersionFileTask>(GenerateVersionFileTask.name) {
                fileName.set("version.txt")
                outputDirectory.set(generatedResourcesDirectory)
            }
            plugins.withType<JavaPlugin> {
                extensions.getByType<SourceSetContainer>().named(SourceSet.MAIN_SOURCE_SET_NAME) {
                    resources.srcDir(generateVersionFileTask)
                }
            }
        }
    }
}
