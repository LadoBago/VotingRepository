package home.learning.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.learning.voting.repository.PingDao;

@Service
public class PingServiceImpl implements PingService {

	@Autowired
	public PingServiceImpl(PingDao pingDao) {
		this.pingDao = pingDao;
	}

	private PingDao pingDao;
	
	@Override
	public String ping() {
		return pingDao.ping();
	}

}
