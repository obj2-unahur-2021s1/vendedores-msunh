package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion (val ciudad: Ciudad){

    val vendedoresDelCentro = mutableListOf<Vendedor>()

    fun agregarVendedor(Vendedor : Vendedor) {

        if (vendedoresDelCentro.contains(Vendedor)) {
            throw Exception("este vendedor ya se encuentra en el centro")
        }else{
            vendedoresDelCentro.add(Vendedor)
        }
    }
    //esta funcion no se pedía pero la agregué para testear
    fun quitarVendedor(Vendedor: Vendedor){
        if(! vendedoresDelCentro.contains(Vendedor)){
            throw Exception ("el vendedor no esta en el centro")
        }else{
            vendedoresDelCentro.remove(Vendedor)
        }
    }

    fun vendedorEstrella() =
        vendedoresDelCentro.maxBy{it.puntajeCertificaciones()}

    fun puedeCubrirCiudad(ciudad: Ciudad) =
        vendedoresDelCentro.any{it.puedeTrabajarEn(ciudad)}

    fun vendedoresGenericosRegistrados() =
        vendedoresDelCentro.filter{it.esGenerico()}

    fun esRobusto() =
        vendedoresDelCentro.filter{ it.esFirme()}.size >= 3

}