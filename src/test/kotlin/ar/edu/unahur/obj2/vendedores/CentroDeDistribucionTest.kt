package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CentroDeDistribucionTest : DescribeSpec({

    //provincias
    val misiones = Provincia(1300000)
    val buenosAires = Provincia(15771581)
    val mendoza = Provincia(1000000)
    val sanLuis = Provincia(4552200)
    val laPampa = Provincia(500000)
    val sanJuan = Provincia(600000)
    //ciudades
    val sanIgnacio = Ciudad(misiones)
    val caucete = Ciudad(misiones)
    val moron = Ciudad(buenosAires)
    val godoyCruz = Ciudad(mendoza)
    val guaymallen = Ciudad(mendoza)
    val junin = Ciudad(mendoza)
    val maipu = Ciudad(mendoza)
    val sanRafael = Ciudad(mendoza)
    val rivadavia = Ciudad(mendoza)
    val chacabuco = Ciudad(sanLuis)
    val coronelPringles = Ciudad(sanLuis)
    val rancul = Ciudad(laPampa)

    //vendedores fijos
    val rick = VendedorFijo(moron)
    val morty = VendedorFijo(moron)
    val mrMeekseek = VendedorFijo(moron)
    val juanjo = VendedorFijo(junin)
    val mario = VendedorFijo(rancul)
    val oscar = VendedorFijo(caucete)
    val fede = VendedorFijo(coronelPringles)

    //centros de distribucion
    val primerCentroDeDistribucion = CentroDeDistribucion(moron)
    val segundoCentroDeDistribucion = CentroDeDistribucion(rivadavia)
    val tercerCentroDeDistribucion = CentroDeDistribucion(caucete)
    //certificaciones
    val certificacion01 = Certificacion(true, 10)
    val certificacion02 = Certificacion(true, 15)
    val certificacion03 = Certificacion(false, 2)
    val certificacion04 = Certificacion(false, 9)
    val certificacion05 = Certificacion(true, 21)
    val certificacion06 = Certificacion(false, 11)

    // certificaciones a vendedores
    //no es firme
    rick.agregarCertificacion(certificacion01)
    rick.agregarCertificacion(certificacion02)
    // no es firme
    morty.agregarCertificacion(certificacion03)
    morty.agregarCertificacion(certificacion06)

    //es generico y es firme
    mrMeekseek.agregarCertificacion(certificacion05)
    mrMeekseek.agregarCertificacion(certificacion04)
    //es generico y no es firme
    mario.agregarCertificacion(certificacion01)
    mario.agregarCertificacion(certificacion06)
    //es generico y es firme
    oscar.agregarCertificacion(certificacion02)
    oscar.agregarCertificacion(certificacion04)
    oscar.agregarCertificacion(certificacion01)
    //no es generico y es firme
    fede.agregarCertificacion(certificacion02)
    fede.agregarCertificacion((certificacion05))

    //agrego vendedores al centro
    primerCentroDeDistribucion.agregarVendedor(rick)
    primerCentroDeDistribucion.agregarVendedor(morty)
    primerCentroDeDistribucion.agregarVendedor(mrMeekseek)

    segundoCentroDeDistribucion.agregarVendedor(oscar)
    segundoCentroDeDistribucion.agregarVendedor(juanjo)
    segundoCentroDeDistribucion.agregarVendedor(mario)

    tercerCentroDeDistribucion.agregarVendedor(mrMeekseek)
    tercerCentroDeDistribucion.agregarVendedor(mario)
    tercerCentroDeDistribucion.agregarVendedor(oscar)
    tercerCentroDeDistribucion.agregarVendedor(rick)
    tercerCentroDeDistribucion.agregarVendedor(fede)


    describe ("agregando vendedores al centro"){
        it ("el vendendor ya esta en el centro"){
            shouldThrowAny {
                primerCentroDeDistribucion.agregarVendedor(morty)
            }
        }
    }

    describe("vendedor estrella"){
        it("vendedor estrella del centro es mr Meekseek"){
            primerCentroDeDistribucion.vendedorEstrella().shouldBe(mrMeekseek)
        }

        it("vendedor estrella del centro no deberia ser morty"){
            primerCentroDeDistribucion.vendedorEstrella().shouldNotBe(morty)
        }
    }

    describe ("puede cubrir ciudad"){
        it ("al menos uno puede cubrir la ciudad"){
            segundoCentroDeDistribucion.puedeCubrirCiudad(rancul).shouldBeTrue()

        }

        it ("ninguno puede cubrir la ciudad"){
            segundoCentroDeDistribucion.puedeCubrirCiudad(moron).shouldBeFalse()
        }

    }


    describe ("lista de vendedores genericos registrados"){
        it("vendedores genericos registrados del centro"){
            tercerCentroDeDistribucion.vendedoresGenericosRegistrados().shouldContainAll(oscar,mrMeekseek,mario)
        }

    }

    describe ("centro es robusto"){
        it("tiene al menos 3 vendedores firmes"){
            tercerCentroDeDistribucion.esRobusto().shouldBeTrue()

        }
    }

    //

    describe ("centro no es robusto"){
        tercerCentroDeDistribucion.quitarVendedor(fede)
        it("no tiene al menos 3 vendedores firmes"){
            tercerCentroDeDistribucion.esRobusto().shouldBeFalse()
        }
    }

    describe("quitar un vendedor que no esta en el centro"){
        it("throw error"){
            shouldThrowAny {
                tercerCentroDeDistribucion.quitarVendedor(morty)
            }
        }
    }


})



