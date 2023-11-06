package lambda_warburton;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BlockingArtistAnalyzer implements ArtistAnalyzer {
    private final Function<String, Artist> artistLookupService;

    public BlockingArtistAnalyzer(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        Supplier<Long> s1 = () -> {
            return artistLookupService.apply(artistName).getMembers().count();
        };
        Supplier<Long> s2 = () -> {
            return artistLookupService.apply(otherArtistName).getMembers().count();
        };

        CompletableFuture<Long> c1 = CompletableFuture.supplyAsync(s1);
        CompletableFuture<Long> c2 = CompletableFuture.supplyAsync(s2);

        c1.thenCombine(c2, (c11, c22) -> c11 > c22).thenAccept(handler);
    }

    private static class Artist {
        public Stream<String> getMembers() {
            return Stream.of((String) null);
        }
    }

}

interface ArtistAnalyzer {
    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler);
}
