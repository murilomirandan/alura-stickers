import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        ContentExtractor extrator;

        String optionDatabase = getDataBaseOption(input);
        if (optionDatabase.isEmpty()) {
            throw new Exception("This DataBase option was not defined.");
        }

        int optionTask = getTaskOption(input);
        input.close();

        var http = new ClientHttp();
        String json = http.searchData(optionDatabase);

        if (optionDatabase.contains("imdb")) {
            extrator = new ContentExtractorIMDb();
        } else if (optionDatabase.contains("nasa")) {
            extrator = new ContentExtractorNasa();
        } else {
            extrator = new ContentExtractorPokemon();
        }
        List<Content> contents = extrator.extractContent(json);

        if (optionTask == 2) {
            createStickers(contents, 10);
        } else {
            showSearch(contents, 10);
        }
    }

    private static int getTaskOption(Scanner input) throws Exception {
        int optionTask;

        System.out.println(Messages.getString("Task.Option"));
        do {
            optionTask = input.nextInt();
            if (optionTask == 0) {
                input.close();
                throw new Exception("User decided to exit");
            }
        } while (!(optionTask > 0 && optionTask <= 2));
        return optionTask;
    }

    private static String getDataBaseOption(Scanner input) throws Exception {
        String url = "";
        String key;
        int optionDatabase;

        System.out.println(Messages.getString("Database.Option"));
        do {
            optionDatabase = input.nextInt();
            switch (optionDatabase) {
                case 1:
                    key = getKey();
                    url = "https://imdb-api.com/en/API/Top250Movies/" + key;
                    break;
                case 2:
                    key = getKey();
                    url = "https://imdb-api.com/en/API/Top250TVs/" + key;
                    break;
                case 3:
                    key = getKey();
                    url = "https://imdb-api.com/en/API/MostPopularMovies/" + key;
                    break;
                case 4:
                    key = getKey();
                    url = "https://api.nasa.gov/planetary/apod?api_key=" + key
                            + "&start_date=2022-05-16&end_date=2022-05-21";
                    break;
                case 5:
                    String nameOrNUmber = getPokemon();
                    url = "https://pokeapi.glitch.me/v1/pokemon/" + nameOrNUmber;
                    break;
                case 0:
                    input.close();
                    throw new Exception("User decided to exit");
                default:
                    System.out.println("This option is not define.");
            }
        } while (!(optionDatabase > 0 && optionDatabase <= 5));
        return url;
    }

    private static String getPokemon() {
        Scanner input = new Scanner(System.in);
        System.out.print(Messages.getString("Pokemon.Option"));
        String nameOrNUmber = input.nextLine();
        return nameOrNUmber;
    }

    private static String getKey() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your key: ");
        String key = input.nextLine();
        return key;
    }

    private static void createStickers(List<Content> contents, int size) throws Exception {
        size = contents.size() > size ? size : contents.size();
        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < size; i++) {
            Content content = contents.get(i);

            String urlImage = content.getUrlImage();
            String title = content.getTitle();
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = title + ".png";

            geradora.cria(inputStream, fileName);
            System.out.println("\u001b[1mTitle: \u001b[m" + title);
            System.out.println("\u001b[1mImage link: \u001b[m" + urlImage);
        }
    }

    private static void showSearch(List<Content> contents, int size) {
        size = contents.size() > size ? size : contents.size();

        for (int i = 0; i < size; i++) {
            Content content = contents.get(i);
            System.out.println("\u001b[1mTitle: \u001b[m" + content.getTitle());
            System.out.println("\u001b[1mImage link: \u001b[m" + content.getUrlImage());
            // System.out.println("\u001b[1mIMDb Rating: \u001b[m" +
            // content.get("imDbRating"));

            // int count = 0;
            // String numberOfStars = "";
            // if (!content.get("imDbRating").isEmpty()) {
            // int stars = (int) Math.round(Double.parseDouble(content.get("imDbRating")));
            // while (count < stars) {
            // numberOfStars += "*"; // \u2B50 star-unicode
            // count++;
            // }
            // }
            // System.out.println(numberOfStars + "\n");
        }
    }
}
