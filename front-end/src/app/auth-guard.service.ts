import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './authenticate/auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  private token: string=null;

  constructor(private authService: AuthService, private router:Router){ }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    this.token=window.localStorage.getItem('token');
    if(this.token!==null){
      return true;
    }
    else{
      console.log(state.url);
     if(state.url=="/employeedashboard")
      {
        console.log(123);
      this.router.navigate(['/loginEmployee']);
      }
      else{
        console.log(345);
        this.router.navigate(['/loginCustomer']);
      }
      
    }
  }
}
