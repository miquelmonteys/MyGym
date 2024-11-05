import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';
import {MediaMatcher} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {MatSidenav} from '@angular/material/sidenav';
import {GenericService} from "./_services/generic.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  roles: string[] = [];
  isLoggedIn = false;
  username?: string;
  opened = true;
  mobileQuery: MediaQueryList;
  @ViewChild("snav") snav: MatSidenav;
  private _mobileQueryListener: () => void;
  user: any;

  languageList = [
    {code: 'en', label: 'English'},
    {code: 'es', label: 'Castellano'}
  ];
  currentLanguage: any;

  constructor(private genericService: GenericService, private translate: TranslateService, public router: Router, changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private tokenStorageService: TokenStorageService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
    if (tokenStorageService.getLanguage()) {
      translate.setDefaultLang(<string>tokenStorageService.getLanguage().code);
      translate.use(<string>tokenStorageService.getLanguage().code);
      this.currentLanguage = tokenStorageService.getLanguage();
    } else {
      this.changeLang(this.languageList[0]);
    }
  }
  changeLang(lang: any) {
    this.tokenStorageService.setLanguage(lang);
    this.currentLanguage = lang;
    this.translate.setDefaultLang(lang.code);
    this.translate.use(lang.code);
  }

  ngOnInit(): void {
    // console.log('app component');
    this.isLoggedIn = this.tokenStorageService.getUser();
    if (this.isLoggedIn) {
      this.user = this.tokenStorageService.getUser();
      console.log(this.user);
      this.roles = this.user.roles;
    } else {
      // this.router.navigateByUrl('/login');
    }
    this.tokenStorageService.getUserUpdate().subscribe(user => {
      this.user = user;
      this.roles = this.user.roles;
    })
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  changeToolbar() {
    this.snav.toggle();
    this.opened = !this.opened;
    this.genericService.emitResize();
  }
}
