package projectzero.fx;

public interface Observable<T> {
    public void register(Observer<T> observer);
    public void unregister(Observer<T> observer);
    public void notifyObservers();
}
