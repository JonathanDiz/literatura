package com.library.book_library.dto;

import java.util.List;

public class PaginatedResponse<T> {
    private long count;
    private String next;
    private String previous;
    private List<T> results;

    public PaginatedResponse() {
    }

    public PaginatedResponse(long count, String next, String previous, List<T> results) {
       this.count = count;
       this.next = next;
       this.previous = previous;
       this.results = results;
    }

    // Getters y setters

    /**
     * @return long return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * @return String return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @return String return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @return List<T> return the results
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

}
