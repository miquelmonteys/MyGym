import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ShellComponent} from "./shell/shell.component";
import {PrincipalComponent} from "./principal/principal.component";
import {CrearRutinaComponent} from "./crear-rutina/crear-rutina.component";
import {IniciarRutinaComponent} from "./iniciar-rutina/iniciar-rutina.component";

const routes: Routes = [

  {path: 'test', component: ShellComponent},
  {path: '', component: PrincipalComponent},
  {path: 'crearRutina', component: CrearRutinaComponent},
  {path: 'iniciarRutina', component: IniciarRutinaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
