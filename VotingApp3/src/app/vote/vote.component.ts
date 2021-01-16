import { Component, OnInit, Input } from '@angular/core';
import { EnvironmentService } from '../_services/environment.service';
import { VoteApiService } from '../_services/vote-api.service';

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrls: ['./vote.component.css']
})
export class VoteComponent implements OnInit {

  @Input() voteName: string;

  constructor(private environmentService: EnvironmentService,
    private voteApi: VoteApiService) {

  }

  ngOnInit(): void {
  }

  onVote()
  {
    console.log(this.voteName);

    this.voteApi
    .vote(this.voteName)
    .subscribe({
        next: (value: string) => {
        console.log('next');
        console.log(value);
      }, error: (error: string) => {
        console.log('error');
        console.log(error);
      }
    });
  }

}
