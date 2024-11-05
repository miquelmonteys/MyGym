import {LOCALE_ID, NgModule} from '@angular/core';
import {CommonModule, registerLocaleData, UpperCasePipe} from '@angular/common';
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatNativeDateModule, MatOptionModule
} from '@angular/material/core';

import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDividerModule} from '@angular/material/divider';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatSortModule} from '@angular/material/sort';
import {MatStepperModule} from '@angular/material/stepper';
import {MatToolbarModule} from '@angular/material/toolbar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CdkDropList, DragDropModule} from '@angular/cdk/drag-drop';
import {MatExpansionModule} from "@angular/material/expansion";
import {SafePipe} from "./safe.pipe";
import es from "@angular/common/locales/es";
import {MatSidenavModule} from "@angular/material/sidenav";
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {HttpClient} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {MatChipSet, MatChipsModule} from '@angular/material/chips';
import {MatMenuModule} from '@angular/material/menu';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatTableModule} from '@angular/material/table';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatListModule} from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatRadioModule} from '@angular/material/radio';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatPaginatorModule} from '@angular/material/paginator';
import {RouterModule} from "@angular/router";
import {NgChartsModule} from "ng2-charts";
import {TokenStorageService} from "../_services/token-storage.service";


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


export const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY'
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY'
  }
};
registerLocaleData(es);

const sharedModules: any[] = [
  // NgxMaterialTimepickerModule,
  // NgxChartsModule,
  MatChipsModule,
  MatTooltipModule,
  MatSlideToggleModule,
  MatMenuModule,
  MatSidenavModule,
  MatIconModule,
  MatToolbarModule,
  MatButtonModule,
  MatExpansionModule,
  DragDropModule,
  MatProgressBarModule,
  FormsModule,
  ReactiveFormsModule,
  CommonModule,
  MatSortModule,
  MatDialogModule,
  MatTableModule,
  MatAutocompleteModule,
  MatListModule,
  MatGridListModule,
  MatOptionModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatCheckboxModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatStepperModule,
  MatTabsModule,
  MatDividerModule,
  MatSnackBarModule,
  MatPaginatorModule,
  RouterModule,
  NgChartsModule
];

const sharedDeclarations: any[] = [
  SafePipe,
];

const sharedComponents: any[] = [];

const sharedProviders: any[] = [
  {provide: MAT_DATE_LOCALE, useValue: 'es-ES'},
  {provide: LOCALE_ID, useValue: "es-ES"},
  {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  // {provide: CalendarDateFormatter, useClass: CustomDateFormatter}

]

@NgModule({
  imports: [
    sharedModules,
    MatChipSet,
    CdkDropList,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      },
      defaultLanguage: 'en',
    }),
    UpperCasePipe
  ],
  declarations: [
    sharedDeclarations,
    sharedComponents
  ],
  providers: [
    sharedProviders,
    TokenStorageService,
  ],
  exports: [
    sharedModules,
    sharedDeclarations,
    sharedComponents,
    TranslateModule
  ]
})
export class SharedModule {
}
