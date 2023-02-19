package entity;

public class Book {

    private int id;
    private String name;
    private int yearWrite;
    private BookType format;
    private int pageCount;
    private int instanceCount;

    public Book(int id, String name, int yearWrite, int pageCount, int instanceCount, BookType format) {
        this.id = id;
        this.name = name;
        this.yearWrite = yearWrite;
        this.format = format;
        this.pageCount = pageCount;
        this.instanceCount = instanceCount;
    }

    public Book(String name, int yearWrite, BookType format, int pageCount) {
        this.name = name;
        this.yearWrite = yearWrite;
        this.format = format;
        this.pageCount = pageCount;
    }

    public Book(String name, int yearWrite, int pageCount) {
        this.name = name;
        this.yearWrite = yearWrite;
        this.pageCount = pageCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearWrite() {
        return yearWrite;
    }

    public void setYearWrite(int yearWrite) {
        this.yearWrite = yearWrite;
    }

    public BookType getFormat() {
        return format;
    }

    public void setFormat(BookType format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearWrite=" + yearWrite +
                ", format='" + format + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}
