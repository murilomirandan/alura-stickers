import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDb implements ContentExtractor {
    
    @Override
    public List<Content> extractContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // add content in the list
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            Content content = new Content(title, urlImage);

            contents.add(content);
        }
        return contents;
    }
}
