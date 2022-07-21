## Getting Started with Imersão Java

## 1. Day
- Create an IMDb account and use the key to make some requests.
- Setup Http Client, Request and Response.
- Use RegEx to get the items in the body of the Http response.
- Show the items from body response.

### Extras: 
- User provides his/her own IMDb-key
- User can select three different URLs (Top 250 Movies, Top 250 TVs and Most Popular Movies)
- Exceptions handling with Try-catch (if the IMDb-key is wrong) and IMDb-Rating doesn't exist (String is empty).

### Problems:
- not showing stars in terminal
- Already tried:
    - Select cmd as default and chcp 65001

## 2. Day
- Use some library to manipulate images (ImageIO, Graphics2D and BufferedImage).
    - increase image size
    - change font (font family, size and style)
    - add a text in the image

- Read data using InputStream
    - Manipulate image link data (instead thumbnail, full image)

![Little Miss Sunshine Output](https://user-images.githubusercontent.com/2809318/179905721-a0adc24e-9185-44ac-a073-cc774ffb57bb.jpg)

## 3. Day
- Refactoring the project
    1. App calls ClientHttp and ClientHttp gives the response.body back.
    2. App sends the body to ContentExtractor (IMDb or NASA):
        1. ContentExtractor calls JsonParser and gives the body.
        2. JsonParser takes the body and puts each element in the Json in a List. Finally JsonParser gives this List (attributeList) to ContentExtractor.
        3. ContentExtractor converts each element List into Content-Object and adds to another List (contents). This List is sent back to the App.
    3. App shows the content of the new List (contents) or sends to GeradoraDeFigurinhas.

## TO DO:
    1. Import Gson or another Json Parser 
    2. Handling Exceptions in ClientHttp
    3. Streams and Lambda to map a list
    4. Create a Enum for configuration (API URL and Extractors)
[https://www.baeldung.com/java-localization-messages-formatting](Messages formatting)

## 4. Day
