package b2_Reflection;

//静态代理

interface ClothFactory {

    void produceCloth();

}

//代理类
class ProxyClothFactory implements ClothFactory {

    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("准备工作");
        factory.produceCloth();
        System.out.println("后续工作");
    }

}

//被代理类
class Nike implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("生产cloth");
    }

}

public class R4 {

    public static void main(String[] args) {

        //被代理类对象
        Nike nike = new Nike();
        //代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();

    }

}
