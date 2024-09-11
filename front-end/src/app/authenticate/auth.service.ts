import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated = new Subject<boolean>();

  constructor(private httpClient: HttpClient,
    private router:Router) { 
  }

  public loginuser(user){
    // this.autoLogout(360000);
    return this.httpClient.post('http://localhost:8084/auth-ms/login',user);
  }

  logout() {
    localStorage.removeItem('userid');
    localStorage.removeItem('token');
    this.router.navigate(['/']);
    this.isAuthenticated.next(false);
  }

}
