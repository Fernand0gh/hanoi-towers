public class Movement {
    private final int origin;
    private final int destination;
    private final Disk disk;

    public Movement(int origin, int destination, Disk disk){
        this.origin = origin;
        this.destination = destination;
        this.disk = disk;
    }
    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    public Disk getDisk() {
        return disk;
    }
}
