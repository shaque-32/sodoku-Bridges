import bridges.connect.KeypressListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashSet;

public class Keyboard implements KeypressListener {
    private HashSet<String> keyDown = new HashSet<>();
    private HashSet<String> keyPress = new HashSet<>();

    public void keypress(JSONObject json) {
        String type = "";
        String key = "";

        try {
            type = (String) json.get("type");
            key = (String) json.get("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(type.equals("keydown")) {
            keyDown.add(key);
            keyPress.add(key);
        }
        else if(type.equals("keyup")) {
            keyDown.remove(key);
        }
    }

    public void update() {
        keyPress.clear();
    }

    public boolean keyDown(String key) {
        return keyDown.contains(key);
    }

    public boolean keyPress(String key) {
        return keyPress.contains(key);
    }
}