package hestr8177.coordinatedisplay;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;

public class CoordinateDisplayModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return CoordinateDisplayConfigScreen::new;
    }
}
