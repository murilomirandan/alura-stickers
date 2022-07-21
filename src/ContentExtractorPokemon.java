import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorPokemon implements ContentExtractor {

    @Override
    public List<Content> extractContent(String json) {       
        JsonParserPokemon parser = new JsonParserPokemon();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // add content in the list
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("name");
            String urlImage = attribute.get("sprite");
            Content content = new Content(title, urlImage);

            contents.add(content);
        }
        return contents;
    }
}
