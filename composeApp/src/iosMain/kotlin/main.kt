import androidx.compose.ui.window.ComposeUIViewController
import com.diphrogram.qstestapp.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
