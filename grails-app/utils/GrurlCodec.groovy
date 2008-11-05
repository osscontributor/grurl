class GrurlCodec {

    static encode = { theTarget ->
        Long.toString(theTarget, Character.MAX_RADIX)
    }

    static decode = { theTarget ->
        Long.parseLong(theTarget, Character.MAX_RADIX)
    }
}