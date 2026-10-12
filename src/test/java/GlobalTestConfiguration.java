import java.awt.*;

import org.mockito.Mockito;

public class GlobalTestConfiguration {

  static {
    Toolkit toolkit = Mockito.mock(Toolkit.class);
    Mockito.when(toolkit.getScreenSize()).thenReturn(new Dimension(1920, 1080));
    Mockito.mockStatic(Toolkit.class, Mockito.CALLS_REAL_METHODS);
    Mockito.when(Toolkit.getDefaultToolkit()).thenReturn(toolkit);
  }
}
