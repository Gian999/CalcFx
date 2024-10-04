package pe.edu.upeu.sysalmacenfx.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.model.Categoria;
import java.util.Scanner;

@Component
public class MainX {
    @Autowired
    CategoriaRepository repository;
    private boolean exit = false;
    public void menu(){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Trabajo de Gianluck Yhudá Pastor Lovón");
            System.out.println("¿Que quieres hacer?");
            System.out.println("1. Crear");
            System.out.println("2. Actualizar");
            System.out.println("3. Listar");
            System.out.println("4. Eliminar");
            System.out.println("5. Salir");
            int op = sc.nextInt();
            switch(op){
                case 1:
                    this.registro();
                    break;
                case 2:
                    this.actualizarCategoria();
                    break;
                case 3:
                    this.listar();
                    break;
                case 4:
                    this.eliminar();
                    break;
                case 5:
                    System.out.println();
                    System.out.print("Saliendo");
                    for (int i = 0; i < 3; i++){
                        try {
                            System.out.print(".");
                            Thread.sleep(1000);
                        }catch (Exception e){}
                    }
                    System.out.println();
                    exit = true;
                    break;
            }
        }while (!exit);
    }
    public void registro(){
        Scanner sc = new Scanner(System.in);
        Categoria ca = new Categoria();
        System.out.print("Nuevo nombre: ");
        String newName = sc.nextLine();
        ca.setNombre(newName);
        Categoria dd = repository.save(ca);
        System.out.println("Reporte: ");
        System.out.println(dd.getIdCategoria() + " " + dd.getNombre());
    }
    public void actualizarCategoria() {
        Scanner sc = new Scanner(System.in);
        Categoria ca = new Categoria();
        System.out.println("Actualizar Categoría");
        System.out.print("Ingrese el ID de la categoría a actualizar: ");
        int searchID = sc.nextInt();
        System.out.print("Nuevo nombre: ");
        sc.nextLine();
        String newName = sc.nextLine();
        ca.setNombre(newName);
        ca.setIdCategoria((long) searchID);
        Categoria dd = repository.save(ca);
        System.out.println("Reporte: ");
        System.out.println(dd.getIdCategoria() + " " + dd.getNombre());

    }
    public void listar(){
        System.out.println("Listado de Categorías:");
        for (Categoria ca : repository.findAll()) {
            System.out.println(ca.getIdCategoria() + " - " + ca.getNombre());
        }
    }
    public void eliminar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Eliminar Categoría");

        System.out.println("Selecciona el Id de la categoría a eliminar:");
        Long searchID = sc.nextLong();

        repository.deleteById(searchID);
        System.out.println("Categoría con ID " + searchID + " eliminada.");
    }

}
