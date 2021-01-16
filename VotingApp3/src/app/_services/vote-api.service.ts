import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnvironmentService } from './environment.service';

@Injectable({
  providedIn: 'root'
})
export class VoteApiService {

  constructor(private http: HttpClient,
    private env: EnvironmentService  ) { }

  vote(name: string) : Observable<any>
  {
    let apiEndpoint : string = new URL(this.env.apiUrlVote, this.env.apiHost).toString();
    return this.http.post(apiEndpoint,
      { name },
      {
        responseType: "text"
      });
  } 
}
