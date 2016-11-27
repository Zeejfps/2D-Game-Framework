import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zeejfps on 11/26/16.
 */
public class BitmapFont {

    private static final Pattern pattern = Pattern.compile("(\\w+)=\"?([^\\s\"]+)");

    public final Info info;
    public final Common common;
    public final Page[] pages;
    public final Char[] chars;

    private BitmapFont(Info info, Common common, Page[] pages, Char[] chars) {
        this.info = info;
        this.common = common;
        this.pages = pages;
        this.chars = chars;
    }

    public static BitmapFont load(String path) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(BitmapFont.class.getClassLoader().getResourceAsStream(path)));

        Info _info = new Info(parseLine(br.readLine()));
        Common _common = new Common(parseLine(br.readLine()));

        Page[] _pages = new Page[_common.pages];
        List<Char> _chars = new ArrayList<>();
        for (int i = 0; i < _common.pages; i++) {
            _pages[i] = new Page(parseLine(br.readLine()));
            int chars = Integer.parseInt(parseLine(br.readLine()).get("count"));
            for (int j = 0; j < chars; j++) {
                _chars.add(new Char(parseLine(br.readLine())));
            }
        }

        return new BitmapFont(_info, _common, _pages, _chars.toArray(new Char[_chars.size()]));
    }

    private static Map<String, String> parseLine(String line) {
        Matcher matcher = pattern.matcher(line);
        Map<String, String> params = new HashMap<>();
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            params.put(key, value);
        }
        return params;
    }

    public static class Padding {
        public final int up, right, down, left;
        private Padding(int up, int right, int down, int left){
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
        }
        @Override
        public String toString() {
            return up + ","+ right + ","+down + ","+left;
        }
    }

    public static class Spacing {
        public final int x, y;
        private Spacing(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    public static class Info {

        public final String face, charset;
        public final int size, stretchH;
        public final boolean isBold, isItalic, isUnicode;
        public final boolean isSmooth, isAA;
        public final Padding padding;
        public final Spacing spacing;

        private Info(Map<String, String> params) {
            face = params.get("face");
            size = Integer.parseInt(params.get("size"));
            isBold = Integer.parseInt(params.get("bold")) == 1;
            isItalic = Integer.parseInt(params.get("italic")) == 1;
            charset = params.get("charset");
            isUnicode = Integer.parseInt(params.get("unicode")) == 1;
            stretchH = Integer.parseInt(params.get("stretchH"));
            isSmooth = Integer.parseInt(params.get("smooth")) == 1;
            isAA = Integer.parseInt(params.get("aa")) == 1;

            String[] tokens = params.get("padding").split(",");
            int up = Integer.parseInt(tokens[0]);
            int right = Integer.parseInt(tokens[1]);
            int down = Integer.parseInt(tokens[2]);
            int left = Integer.parseInt(tokens[3]);
            padding = new Padding(up, right, down, left);

            tokens = params.get("spacing").split(",");
            int x = Integer.parseInt(tokens[0]);
            int y= Integer.parseInt(tokens[1]);
            spacing = new Spacing(x, y);
        }
    }

    public static class Common {

        public final int pages, lineHeight, base;
        public final int scaleW, scaleH;
        public final boolean packed;

        public Common(Map<String, String> params) {
            lineHeight = Integer.parseInt(params.get("lineHeight"));
            base = Integer.parseInt(params.get("base"));
            pages = Integer.parseInt(params.get("pages"));
            scaleW = Integer.parseInt(params.get("scaleW"));
            scaleH = Integer.parseInt(params.get("scaleH"));
            packed = Integer.parseInt(params.get("packed")) == 1;
        }
    }

    public static class Page {

        public final int id;
        public final String file;

        public Page(Map<String, String> params) {
            id = Integer.parseInt(params.get("id"));
            file = params.get("file");
        }

        @Override
        public String toString() {
            return id + "," + file;
        }
    }

    public static class Char {

        public final int id;

        public Char(Map<String, String> params) {
            this.id = Integer.parseInt(params.get("id"));
        }
    }

}
