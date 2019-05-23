import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import static java.awt.event.KeyEvent.*;

public class main
		implements NativeKeyListener {
	
	public static void main(String[] args) {
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.out.println("Error");
		}
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		
		if(e.getKeyCode() == VK_RIGHT)
			System.out.println("RIGHT");
		
		else if (e.getKeyCode() == VK_LEFT)
			System.out.println("LEFT");
		
		else if (e.getKeyCode() == VK_UP)
			System.out.println("UP");
		
		else if (e.getKeyCode() == VK_DOWN)
			System.out.println("DOWN");
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	@Override
	//Essentially useless
	public void nativeKeyReleased(NativeKeyEvent e) {
	
	}
	
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
}
