
/**
 *
     从data.bat文件中排序，找到数量最大的10个数字，以println的方式分别输出。
     要求：
     0.时间尽量短
     1.类名为Test.java
     2.无包名
     3.无第三方引用
     4.可直接javac编译和java运行
     5.java命令行运行class，参数为data.bat文件路径，例如，java Test ./data.bat
     6.jdk1.8
     7.文件字符集，utf-8
     8.不能有异常和警告
 *
 * @author : zshuang
 * @version : 1.0.0
 * @date : 2020/4/7 0007 17:54
 */
public class Test {

    public static int N = 10;           // Top10
    public static int arr[] = new int[N];
    //数组长度
    public static int len = arr.length;
    //堆中元素的有效元素 heapSize<=len
    public static int heapSize = len;

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int _150M = 1024*1024*150;
        File fin = new File("F:\\IdeaProjects\\snowflake\\src\\main\\java\\com\\example\\snowflake\\data.dat");
        FileChannel fcin = null;
        try {
            fcin = new RandomAccessFile(fin, "r").getChannel();
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件");
            return;
        }
        ByteBuffer rBuffer = ByteBuffer.allocate(_150M);
        readFileByLine(_150M, fcin, rBuffer);
        System.err.print( "使用时间"+(System.currentTimeMillis() - start) +"ms");
    }
    public static void readFileByLine(int bufSize, FileChannel fcin,
                                      ByteBuffer rBuffer) {
        String enterStr = "\n";
        try {
            byte[] bs = new byte[bufSize];
            String tempString = null;
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex = 0;
                int  i = 0 ;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    fromIndex = endIndex + 1;
                    if (i<10){
                        arr[i]= Integer.valueOf(line);
                        i++;
                        continue;
                    }
                    //构建小顶堆
                    buildMinHeap();
                    if(Integer.valueOf(line) > arr[0]){
                        arr[0] = Integer.valueOf(line);
                        minHeap(0);
                    }
                }
                print();
            }
        } catch (IOException e) {
            System.out.println("读取文件失败");
            return;
        }
    }
    /**
     * 自底向上构建小堆
     */
    public static void buildMinHeap(){
        int size = len / 2;
        for(int i = size;i>=0;i--){
            minHeap(i);
        }
    }

    /**
     * i节点为根及子树是一个小堆
     * @param i
     */
    public static void minHeap(int i){
        int l = left(i);
        int r = right(i);
        int index = i;
        if(l<heapSize && arr[l]<arr[index]){
            index = l;
        }
        if(r<heapSize && arr[r]<arr[index]){
            index = r;
        }
        if(index != i){
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
            //递归向下构建堆
            minHeap(index);
        }
    }

    /**
     * 返回i节点的左孩子
     * @param i
     * @return
     */
    public static int left(int i){
        return 2*i;
    }

    /**
     * 返回i节点的右孩子
     * @param i
     * @return
     */
    public static int right(int i){
        return 2*i+1;
    }
    /**
     * 打印
     */
    public  static void print(){
        for (int i = arr.length -1; i >=0 ; i--) {
            System.out.println(arr[i]);
        }
    }
}
