package app.revanced.clis

import app.revanced.utils.adb.Adb
import picocli.CommandLine.*
import java.io.File

@Command(
    name = "ReVanced-clis", version = ["1.0.0"], mixinStandardHelpOptions = true
)
internal object MainCommand : Runnable {
    @Option(
        names = ["-c", "--clean"],
        description = ["Clean the temporal resource cache directory. This will be done anyways when running the patcher"]
    )
    internal var clean: Boolean = false

    @Option(names = ["-d", "--deploy-on"], description = ["If specified, deploy to adb device with given name"])
    internal var deploy: String? = null

    override fun run() {
        val outputFile = File("./revanced.apk")
        deploy?.let {
            Adb(
                outputFile,
                "com.google.android.youtube",
                deploy!!
            ).deploy()
        }

        if (clean) outputFile.delete()
    }
}