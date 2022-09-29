package com.frontend.ejercicios.Controller;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Entity.Transaction;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Service.ServiceEmployee;
import com.frontend.ejercicios.Service.ServiceEnterprise;
import com.frontend.ejercicios.Service.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Transaction")
public class ControllerTransaction {
    @Autowired
    ServiceTransaction serviceTransaction;
    ServiceEmployee serviceEmployee;
    ServiceEnterprise serviceEnterprise;

    public ControllerTransaction(ServiceTransaction serviceTransaction, ServiceEmployee serviceEmployee, ServiceEnterprise serviceEnterprise){
        this.serviceTransaction= serviceTransaction;
        this.serviceEmployee= serviceEmployee;
        this.serviceEnterprise= serviceEnterprise;
    }

    @GetMapping("/movimientos") //1 profe JuanMa
    public String consultarMovimientos(Model model){
        List<Transaction> consultarMovimientos=serviceTransaction.consultarTransacciones();
        model.addAttribute("consultarMovimientos", consultarMovimientos);
        return "/Transaction/consultarMovimientos";
    }

    @GetMapping("/nueva")
    public String boton(){
        return "/Transaction/nueva";
    }


    @PostMapping("/guardar")
    public String guardarMovimiento(@RequestParam ("amount") Float amount , @RequestParam("concept") String concept, @RequestParam("LocalDate")LocalDate createdAt){ //traigo el id porque lo usé en el servicio anterior
        serviceTransaction.guardarIngresos(amount,concept,createdAt,this.serviceEmployee,this.serviceEnterprise, this.serviceTransaction);
        return "redirect:/Transaction/movimientos";
    }






    @PostMapping("/eliminar")
    public String eliminarTransaction(@ModelAttribute Transaction transaction){
        serviceTransaction.eliminarTransacciones(transaction.getId());
        //refrescar la lista de empleados
        return "redirect:/Transaction/movimientos";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute @Valid Transaction transaction,
                             BindingResult bindingResult, Model model) {

        Optional<Transaction> transaction1= serviceTransaction.consultarMovimientos(transaction.getId());

        if(transaction1.isPresent()){
            transaction= transaction1.get();
            model.addAttribute("transaction", transaction);
            model.addAttribute("user", transaction.getUser());
            model.addAttribute("enterprise",transaction.getEnterprise());
        }
        return "/Transaction/editar";
    }

    @RequestMapping(value="/actualizarMovimiento", method = RequestMethod.POST)
    public String actualizarMovimiento(@DateTimeFormat(pattern="yyyy-mm-dd") @ModelAttribute("transaction")  Transaction transaction, @DateTimeFormat(pattern="yyyy-mm-dd") @ModelAttribute("user")  Employee user,
                             @DateTimeFormat (pattern="yyyy-mm-dd") @ModelAttribute("enterprise") Enterprise enterprise, @RequestParam("idEmployee") Long idEmployee , @RequestParam("idEnterprise") Long idEnterprise , @RequestParam("amount") Float amount, @RequestParam("concept")  String concept,
                             @RequestParam("createdAt") LocalDate createdAt, Model model) {


        transaction.setAmount(amount);
        transaction.setConcept(concept);
        transaction.setCreatedAt(createdAt);





        user.setId(idEmployee);
        user.setCreatedAt(createdAt);
        user.setId(idEnterprise);


        //Estas son las relaciones
        transaction.setEnterprise(enterprise);
        transaction.setUser(user);
        transaction.getEnterprise().setId(idEnterprise);

        this.serviceTransaction.crearIngresos(transaction);
        this.serviceEmployee.crearUsuario(user);
        this.serviceEnterprise.crearEmpresa(enterprise);

        return "redirect:/Transaction/movimientos";

/*
    @PostMapping("/enterprises/{id}/movements")
    public void crearMovimientos(@RequestBody Transaction transaction){
        serviceTransaction.crearMovimientos(transaction);
    }
    @GetMapping("/enterprises/{id}/movements")
    public List<Transaction> consultarTransacciones(){
        return serviceTransaction.consultarTransacciones();
    }
    @DeleteMapping("/enterprises/{id}/movements")
    public void eliminarTransacciones(@PathVariable("id") Long id){
        serviceTransaction.eliminarTransacciones(id);
    }
    @PatchMapping("/enterprises/{id}/movements")
    public void actualizarTransacciones(@RequestBody Transaction transaction){
        serviceTransaction.crearMovimientos(transaction);*/
    }
}
