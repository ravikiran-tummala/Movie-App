package ravikirantummala.movieapp.Services;

/**
 * Created by ravikirantummala on 04/02/18.
 */

public interface PaginationListener {
    void onPageFetcherResponse(int totalPages,int totalResults);
    void onErrorResponse(Exception error);
}
