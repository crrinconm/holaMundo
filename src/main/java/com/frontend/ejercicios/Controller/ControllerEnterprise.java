package com.frontend.ejercicios.Controller;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Service.ServiceEnterprise;
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
@RequestMapping("/Enterprise")
public class ControllerEnterprise {
    @Autowired
    ServiceEnterprise serviceEnterprise;



    @GetMapping("/empresa") //1 profe JuanMa
    public String consultarEmpresas(Model model){
        List<Enterprise> listarEmpresas=serviceEnterprise.consultarEmpresa();
        model.addAttribute("listarEmpresas", listarEmpresas);
        return "/Empresa/consultarEmpresas";
    }


    @PostMapping("/guardar")
    public String crearEmpresa (@RequestParam ("name") String name ,@RequestParam("document") String document, @RequestParam("phone")String phone){ //traigo el id porque lo usé en el servicio anterior
        serviceEnterprise.guardarEmpresas(phone,name,document,serviceEnterprise);
        return "redirect:/Enterprise/empresa";
    }


    @GetMapping("/nuevaEmpresa")
    public String boton(){
        return "/Empresa/nuevaEmpresa";
    }



    @PostMapping("/eliminar")
    public String eliminar(@ModelAttribute Enterprise enterprise){
        serviceEnterprise.eliminarEmpresa(enterprise.getId());
        //refrescar la lista de empleados
        return "redirect:/Enterprise/empresa";
    }


    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute @Valid Enterprise enterprise,
                             BindingResult bindingResult, Model model) {

        Optional<Enterprise> enterprise1= serviceEnterprise.buscarEmpresa(enterprise.getId());

        if(enterprise1.isPresent()){
            enterprise= enterprise1.get();
            model.addAttribute("enterprise", enterprise);
            //model.addAttribute("profile",enterprise.getProfile()); No se relacionan
            //model.addAttribute("enterprise",enterprise.getEnterprise()); No se relacionan
        }
        return "/Empresa/editar";
    }

    @RequestMapping(value="/actualizarEmpresa", method = RequestMethod.POST)
    public String actualizar(@DateTimeFormat (pattern="yyyy-mm-dd") @ModelAttribute("enterprise") Enterprise enterprise, @RequestParam("name") String name, @RequestParam("document")  String document,
                             @RequestParam("phone") String phone,
                             Model model) {


        enterprise.setName(name);
        enterprise.setDocument(document);
        enterprise.setPhone(phone);
        enterprise.setUpdatedAt(LocalDate.now());
        enterprise.setCreatedAt(LocalDate.now());

        serviceEnterprise.crearEmpresa(enterprise);
        return "redirect:/Enterprise/empresa";
    }



    /*@PostMapping("/enterprises")
    public void crearEmpresa(@RequestBody Enterprise enterprise){
        serviceEnterprise.crearEmpresa(enterprise);
    }

    @GetMapping("/enterprises")
    public List<Enterprise> consultarEmpresas(){
        return serviceEnterprise.consultarEmpresas();
    }

    @GetMapping("/enterprises/{id}")
    public Optional<Enterprise> consultarEmpresa (@PathVariable("id") Long id){
        return serviceEnterprise.consultarEmpresa(id);
    }

    @PatchMapping("/enterprises")
    public void editarEmpresa(@RequestBody Enterprise enterprise){
        serviceEnterprise.crearEmpresa(enterprise);
    }

    @DeleteMapping("/enterprises/{id}")
    public void eliminarEmpresa(@PathVariable ("id") Long id){
        serviceEnterprise.eliminarEmpresa(id);
    }*/

}
