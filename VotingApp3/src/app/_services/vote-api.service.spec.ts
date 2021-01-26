import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { VoteApiService } from './vote-api.service';

describe('VoteApiService', () => {
  let service: VoteApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(VoteApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
