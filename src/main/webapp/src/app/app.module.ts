import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClient, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';


import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from './shared/shared.module';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {ServiceWorkerModule} from '@angular/service-worker';
import {environment} from '../environments/environment';
import {authInterceptorProviders} from "./_helpers/auth.interceptor";
import {ShellComponent} from "./shell/shell.component";
import {CdkListbox} from "@angular/cdk/listbox";
import {MatRadioModule} from "@angular/material/radio";
import {PrincipalComponent} from "./principal/principal.component";
import {RoutineCardComponent} from "./routine-card/routine-card.component";
import {CrearRutinaComponent} from "./crear-rutina/crear-rutina.component";
import {DialogAfegirExerciciComponent} from "./dialog-afegir-exercici/dialog-afegir-exercici.component";
import {DialogEditarExerciciComponent} from "./dialog-editar-exercici/dialog-editar-exercici.component";
import {DialogEliminarExerciciComponent} from "./dialog-eliminar-exercici/dialog-eliminar-exercici.component";
import {IniciarRutinaComponent} from "./iniciar-rutina/iniciar-rutina.component";
import {DialogComptadorDescansComponent} from "./dialog-comptador-descans/dialog-comptador-descans.component";


@NgModule({
  declarations: [
    PrincipalComponent,
    RoutineCardComponent,
    AppComponent,
    ShellComponent,
    CrearRutinaComponent,
    DialogAfegirExerciciComponent,
    DialogEditarExerciciComponent,
    DialogEliminarExerciciComponent,
    IniciarRutinaComponent,
    DialogComptadorDescansComponent,
  ],
  bootstrap: [AppComponent],
  imports: [
    SharedModule,
    BrowserModule,
    AppRoutingModule,
    MatRadioModule,
    FormsModule,
    BrowserAnimationsModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
    CdkListbox,
  ],
  exports: [
  ],
  providers: [authInterceptorProviders, {
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  }, provideHttpClient(withInterceptorsFromDi())]
})
export class AppModule {
}
