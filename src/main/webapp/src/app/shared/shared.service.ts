import {EventEmitter, Injectable} from '@angular/core';
import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';
import {MatPaginator} from '@angular/material/paginator';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private numNovesIncidencies: number;
  private novaIncidenciaEmitter = new EventEmitter<number>();

  updatePaginator(dataSource, paginator: MatPaginator, element: any) {
    const pageIndex = dataSource.paginator.pageIndex;
    const pageSize = dataSource.paginator.pageSize;
    const indexInPage = dataSource.filteredData.indexOf(element);
    const absoluteIndex = pageIndex * pageSize + indexInPage;
    paginator.pageIndex = Math.floor(absoluteIndex / pageSize) + 1;
    paginator.page.emit();
  }

  validateTreballador(proveidors): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) {
        return null;
      }

      const passwordValid = value && value.id;

      return !passwordValid ? {passwordStrength: true} : null;
    }
  }

  getSortingDataAccessor() {
    return (item, property) => {
      if (typeof item[property] === 'object' && item[property] !== null) {
        if (item[property].nom) {
          return item[property].nom;
        } else if (item[property].descripcio) {
          return item[property].descripcio;
        } else if (item[property].nomTipus) {
          return item[property].nomTipus;
        }
      }
      switch (property) {
        case 'inici':
          return new Date(item.inici);
        case 'fi':
          return new Date(item.fi);
        default:
          return item[property];
      }
    }
  }

  nestedFilterCheck(search, data, key) {
    if (typeof data[key] === 'object') {
      for (const k in data[key]) {
        if (data[key][k] !== null) {
          search = this.nestedFilterCheck(search, data[key], k);
        }
      }
    } else {
      search += data[key];
    }
    return search;
  }


  getFilterPredicate() {
    return (data, filter: string) => {
      const accumulator = (currentTerm, key) => {
        return this.nestedFilterCheck(currentTerm, data, key);
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      // Transform the filter by converting it to lowercase and removing whitespace.
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
  }

  setNumNovesIncidencies(num: number) {
    this.numNovesIncidencies = num;
    this.novaIncidenciaEmitter.emit(num);
  }
  getNumNovesIncidencies() {
    return this.numNovesIncidencies;
  }
  getNumNovesIncidenciesEmmiter() {
    return this.novaIncidenciaEmitter;
  }

}
