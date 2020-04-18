
public class Test {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        TreeMap<Integer,Integer> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        BufferedReader br=new BufferedReader(new FileReader("F:\\IdeaProjects\\snowflake\\src\\main\\java\\com\\example\\snowflake\\data.dat"));//读取文件
        //输出
        for(String s=br.readLine();s!=null;s=br.readLine()) {
            treeMap.put(Integer.parseInt(s), Integer.parseInt(s));
            if(treeMap.size() > 10){
                treeMap.remove(treeMap.lastKey());
            }
        }
        Iterator<Map.Entry<Integer,Integer>> it = treeMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer,Integer> entry = it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.err.print( "使用时间"+(System.currentTimeMillis() - start) +"ms");
    }
}
