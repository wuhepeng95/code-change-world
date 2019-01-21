package fanxintest.demo1;

import java.io.Serializable;

interface Show<A, B, C> {
    <T extends Object & Serializable> T show(A a, B b, C c);
}