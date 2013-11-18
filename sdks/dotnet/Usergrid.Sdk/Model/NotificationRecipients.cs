using System;

namespace Usergrid.Sdk.Model
{
	public class NotificationRecipients : INotificationRecipients
	{
		string _userName;
		string _userUuid;
		string _userQuery;
		string _groupPath;
		string _groupQuery;
		string _deviceName;
		string _deviceQuery;

		public INotificationRecipients AddUserWithName (string name)
		{
            if (_userUuid != null)
                throw new ArgumentException("User name and uuid can not be added at the same time.");
            _userName = name;
			return this;
		}

		public INotificationRecipients AddUserWithUuid (string uuid)
		{
			if (_userName != null)
				throw new ArgumentException ("User name and uuid can not be added at the same time.");

			_userUuid = uuid;
			return this;
		}

		public INotificationRecipients AddUserWithQuery (string query)
		{
            if (_userName != null || _userUuid != null)
                throw new ArgumentException("User query can not be added together with user name or uuid.");
            
            _userQuery = query;
			return this;
		}

		public INotificationRecipients AddGroupWithPath (string path)
		{
            if (_groupQuery != null)
                throw new ArgumentException("Group path and query can not be added at the same time.");
            
            _groupPath = path;
			return this;
		}

		public INotificationRecipients AddGroupWithQuery (string query)
		{
            if (_groupPath != null)
                throw new ArgumentException("Group path and query can not be added at the same time.");
            
            _groupQuery = query;
			return this;
		}

		public INotificationRecipients AddDeviceWithName (string name)
		{
            if (_deviceQuery != null)
                throw new ArgumentException("Device name and query can not be added at the same time.");
            
            _deviceName = name;
			return this;
		}

		public INotificationRecipients AddDeviceWithQuery (string query)
		{
            if (_deviceName != null)
                throw new ArgumentException("Device name and query can not be added at the same time.");
            
            _deviceQuery = query;
			return this;
		}

		public string BuildQuery()
		{
			var query = string.Empty;

			if (_groupPath != null)
			{
				query += string.Format ("/groups/{0}", _groupPath);
			}

			if (_groupQuery != null)
			{
				query += string.Format ("/groups;ql={0}", _groupQuery);
			}

			if (_userName != null)
			{
				query += string.Format ("/users/{0}", _userName);
			}

			if (_userUuid != null)
			{
				query += string.Format ("/users/{0}", _userUuid);
			}

			if (_userQuery != null)
			{
				query += string.Format ("/users;ql={0}", _userQuery);
			}

			if (_deviceName != null)
			{
				query += string.Format ("/devices/{0}", _deviceName);
			}

			if (_deviceQuery != null)
			{
				query += string.Format ("/devices;ql={0}", _deviceQuery);
			}

			query += "/notifications";

			return query;
		}
	}
}

