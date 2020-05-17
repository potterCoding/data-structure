package array;

/**
 * @author sun
 * @date 2020/3/24 22:29
 * @description
 */
public class Student {
    private String name;
    private int score;

    public Student(String studentName, int studentScore) {
        this.name = studentName;
        this.score = studentScore;
    }

    public String toString() {
        return String.format("array.Student(name: %s, score: %d)", this.name, this.score);
    }

    public static void main(String[] args) {
        Array arr = new Array();
        //执行添加元素操作 --- 新增到元素末尾
        arr.addLast(new Student("Lucy", 100));
        arr.addLast(new Student("Bob", 66));
        arr.addLast(new Student("Tina", 88));
        System.out.println(arr);

        //删除指定位置的元素
        arr.remove(2);
        System.out.println(arr);

        //将新元素插入到指定位置
        arr.add(1,new Student("LiHua", 75));
        System.out.println(arr);
    }
}
