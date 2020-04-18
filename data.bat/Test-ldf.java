
public class Test {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        readFileByLines();
        System.err.print("使用时间" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */

    public static void readFileByLines() {
        int[] s = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        File file = new File("F:\\IdeaProjects\\snowflake\\src\\main\\java\\com\\example\\snowflake\\data.dat");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                int temp = Integer.parseInt(tempString);
                int t = temp > s[0] ? jh(0, temp, s) : (temp > s[1] ? jh(1, temp, s) : (temp > s[2] ? jh(2, temp, s) : (temp > s[3] ? jh(3, temp, s) : (temp > s[4] ? jh(4, temp, s) : (temp > s[5] ? jh(5, temp, s) : (temp > s[6] ? jh(6, temp, s) : (temp > s[7] ? jh(7, temp, s) : (temp > s[8] ? jh(8, temp, s) : (temp > s[9] ? jh(9, temp, s) : 0)))))))));
                line++;
            }
            System.out.println(s[0] + "\n" + s[1] + "\n" + s[2] + "\n" + s[3] + "\n" + s[4] + "\n" + s[5] + "\n" + s[6] + "\n" + s[7] + "\n" + s[8] + "\n" + s[9]);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static int jh(int index, int temp, int[] s) {
        int a = index;
        for (int i = 0; i < 9 - index; i++) {
            stamp(i, s);
        }
        s[a] = temp;
        return 0;
    }

    private static void stamp(int index, int[] s) {
        s[9 - index] = s[8 - index];
    }

}
