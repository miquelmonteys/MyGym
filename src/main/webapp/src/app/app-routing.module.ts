import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ShellComponent} from "./shell/shell.component";
import {PrincipalComponent} from "./principal/principal.component";

const routes: Routes = [

  {path: 'test', component: ShellComponent},
  {path: '', component: PrincipalComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
