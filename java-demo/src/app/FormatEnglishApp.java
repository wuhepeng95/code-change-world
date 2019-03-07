package app;

/**
 * Created by wuhp on 2018/2/26
 */
public class FormatEnglishApp {
    public static void main(String[] args) {
        String text = "（1）可以作为一个大型分布式集群（数百台服务器）技术，处理PB级数据，服务大公司；也可以运行在单机上\n" +
                "\n" +
                "，服务小公司\n" +
                "（2）Elasticsearch不是什么新技术，主要是将全文检索、数据分析以及分布式技术，合并在了一起，才形成\n" +
                "\n" +
                "了独一无二的ES；lucene（全文检索），商用的数据分析软件（也是有的），分布式数据库（mycat）\n" +
                "（3）对用户而言，是开箱即用的，非常简单，作为中小型的应用，直接3分钟部署一下ES，就可以作为生产环\n" +
                "\n" +
                "境的系统来使用了，数据量不大，操作不是太复杂\n" +
                "（4）数据库的功能面对很多领域是不够用的（事务，还有各种联机事务型的操作）；特殊的功能，比如全文检\n" +
                "\n" +
                "索，同义词处理，相关度排名，复杂数据分析，海量数据的近实时处理；Elasticsearch作为传统数据库的一个\n";
        System.out.println(text.replaceAll("\\n", "").replaceAll(" +", "").replaceAll("\\. ", ".").replaceAll(": ", ":").replaceAll(", ", ","));
    }
}
