import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ContactsChallenge {

    private  static Map<String, Integer> subsCount = new HashMap<>();

    public static List<Integer> contacts(List<List<String>> queries) {
        List<Integer> response = new ArrayList<>();
        queries.forEach(query -> {
            if(Objects.equals(query.get(0), "add")){
                for (int i = 1; i <= query.get(1).length(); i++){
                    String sub = query.get(1).substring(0, i);
                    subsCount.merge(sub, 1, Integer::sum);
                }
            } else {
                if(subsCount.get(query.get(1)) == null)
                    response.add(0);
                else
                    response.add(subsCount.get(query.get(1)));
            }
        });



//        List<Integer> response =  new ArrayList<>();
//        queries.forEach(query -> {
//            if(query.stream().findFirst().isEmpty())
//                return;
//            if(Objects.equals(query.stream().findFirst().get(), "add"))
//                contactList.add(query.get(query.size() - 1));
//            if (Objects.equals(query.stream().findFirst().get(), "find"))
//                response.add(findContact(query));
//        });
        return response;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> queries = new ArrayList<>();

        IntStream.range(0, queriesRows).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = contacts(queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

}
