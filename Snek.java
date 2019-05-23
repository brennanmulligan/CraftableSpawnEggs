import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snek
		implements NativeKeyListener {

	public static void main(String[] args) throws InterruptedException{

		// Turns off the red output and keeps the cmd clear of gross stuff.
		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);

		// Runs on a seperate thread than the rest of your program, so wont be blocked by the rest of your program (e.g. the main while loop)
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				GlobalScreen.addNativeKeyListener(new Snek());
			}
		};

		try {
			GlobalScreen.registerNativeHook();
			thread.start();
		} catch (NativeHookException ex) {
			System.out.println("Error");
		}

		// Write the rest of your program here. Yeah.
		while(true)
		{
			System.out.println("Test");
			try {
				Thread.sleep(500);
			}
			catch(Exception ex) {}
		}
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT)
			System.out.println("RIGHT");

		else if (e.getKeyCode() == NativeKeyEvent.VC_LEFT)
			System.out.println("LEFT");

		else if (e.getKeyCode() == NativeKeyEvent.VC_UP)
			System.out.println("UP");

		else if (e.getKeyCode() == NativeKeyEvent.VC_DOWN)
			System.out.println("DOWN");
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	@Override
	//Essentially useless
	public void nativeKeyReleased(NativeKeyEvent e) {}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
}
