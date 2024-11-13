import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ShellComponent} from "./shell/shell.component";
import {PrincipalComponent} from "./principal/principal.component";
import {CrearRutinaComponent} from "./crear-rutina/crear-rutina.component";

const routes: Routes = [

  {path: 'test', component: ShellComponent},
  {path: '', component: PrincipalComponent},
  {path: 'crearRutina', component: CrearRutinaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
