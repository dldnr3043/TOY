package com.myproject.toy.common.core.sequence.app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SequenceServiceImpl implements SequenceService {

	@Override
	public String createSequence(String separator) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

		
		return strToday + separator + uuid;
	}

}
