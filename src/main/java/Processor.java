public interface Processor<T> {
    /**
     * Process node of defined type
     * @param node to process
     */
    public void process(T node);
}
