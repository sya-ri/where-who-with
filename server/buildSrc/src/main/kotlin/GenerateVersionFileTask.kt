import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property

/**
 * バージョンファイルを生成するタスク
 */
open class GenerateVersionFileTask : DefaultTask() {
    /**
     * ファイル名
     */
    @Input
    val fileName: Property<String> = project.objects.property()

    /**
     * バージョン情報
     */
    @Input
    val version: Property<String> = project.objects.property()

    /**
     * ファイルを出力するディレクトリ
     */
    @OutputDirectory
    val outputDirectory: DirectoryProperty = project.objects.directoryProperty()

    /**
     * タスクの処理
     */
    @TaskAction
    fun execute() {
        outputDirectory.file(fileName).get().asFile.writeText(version.get())
    }

    companion object {
        /**
         * タスク名
         */
        const val name = "generateVersionFile"
    }
}
