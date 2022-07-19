import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your key: ");
        String key = input.nextLine();
        String url = null;
        int option;

        System.out.println(
                "Welcome to IMDb!\nEnter 1 - Top 250 Movies\nEnter 2 - Top 250 TVs\nEnter 3 - Most Popular Movies\nEnter 4 - Create Stickers\nEnter 0 - Exit");
        do {
            option = input.nextInt();
            switch (option) {
                case 1:
                    url = "https://imdb-api.com/en/API/Top250Movies/" + key;
                    break;
                case 2:
                    url = "https://imdb-api.com/en/API/Top250TVs/" + key;
                    break;
                case 3:
                    url = "https://imdb-api.com/en/API/MostPopularMovies/" + key;
                    break;
                case 4:
                    url = "https://imdb-api.com/en/API/Top250Movies/" + key;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("This option is not define.");
            }
        } while (!(option >= 0 && option <= 4));

        input.close();
        try {
            URI address = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();

            JsonParser parser = new JsonParser();
            List<Map<String, String>> filmsList = parser.parse(body);
            if(option == 4){
                createStickers(filmsList);
            }else{
                showSearch(filmsList);
            }
            
        } catch (Exception e) {
            System.out.println("Key is wrong! Please, try again.");
        }
    }

    private static void createStickers(List<Map<String, String>> filmsList) throws Exception{
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> film : filmsList) {  
            String urlImage = film.get("image");
            String title  = film.get("title");

            InputStream inputStream = new URL(urlImage).openStream() ;
            String fileName = title + ".png";

            geradora.cria(inputStream, fileName);
            System.out.println("\u001b[1mTitle: \u001b[m" + title);
        }
    }

    private static void showSearch(List<Map<String, String>> filmsList) {
        for (Map<String, String> film : filmsList) {
            System.out.println("\u001b[1mTitle: \u001b[m" + film.get("title"));
            System.out.println("\u001b[1mImage link: \u001b[m" + film.get("image"));
            System.out.println("\u001b[1mIMDb Rating: \u001b[m" + film.get("imDbRating"));

            int count = 0;
            String numberOfStars = "";
            if (!film.get("imDbRating").isEmpty()) {
                int stars = (int) Math.round(Double.parseDouble(film.get("imDbRating")));
                while (count < stars) {
                    numberOfStars += "*"; // \u2B50 star-unicode 
                    count++;
                }  
            }
            System.out.println(numberOfStars + "\n");

        }
    }
}
